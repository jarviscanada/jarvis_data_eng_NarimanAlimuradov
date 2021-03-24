-- Nariman Alimuradov
-- This file contains some sample queries that can be used to obtain machine usage and specification information

-- helper function that will round each timestamp to the nearest fifth minute
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

-- query that groups machines by their cpu_number as well as sorts by their memory size (descending)
SELECT 
	cpu_number, 
	id AS host_id, 
	total_mem 
FROM host_info
ORDER BY cpu_number, total_mem DESC;

-- query that calculates the average memory usage every five minutes for each machine
SELECT 
	host_usage.host_id, 
	host_info.hostname as host_name, 
	round5(host_usage.timestamp) AS timestamp, 
	CAST(100 - AVG(host_usage.memory_free) / (host_info.total_mem/1000) AS INT) AS avg_used_mem_percentage
FROM host_usage JOIN host_info ON host_usage.host_id = host_info.id 
GROUP BY round5(host_usage.timestamp), host_usage.host_id, host_info.hostname, host_info.total_mem
ORDER BY host_usage.host_id, round5(host_usage.timestamp);

-- query that checks to see if the server has failed
-- this is determined by checking if the cron job is regularly inserting new entries
SELECT 
	host_id,
	round5(timestamp) AS timestamp,
	COUNT(round5(timestamp)) AS num_data_points
FROM host_usage
GROUP BY host_id, round5(timestamp)
HAVING COUNT(round5(timestamp)) < 3
ORDER BY host_id;
