#!/bin/bash
# Nariman Alimuradov

# first, we get the database credentials
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# check to make sure correct number of arguments were given
if [ "$#" -ne 5 ]; then
    echo "Incorrect number of arguments given"
    exit 1
fi

# next, we parse the machine usage information
memory_free=$(free -m | egrep "^Mem:" | awk '{print $4}' | xargs)
cpu_idle=$(vmstat --unit M | egrep -v 'cpu|id' | awk '{ print $15 }' | xargs)
cpu_kernel=$(vmstat --unit M | egrep -v 'cpu|id' | awk '{ print $14 }' | xargs)
disk_io=$(vmstat --unit M | egrep -v 'io|bi' | awk '{ print $9+$10 }' | xargs)
disk_available=$(df -BM / | egrep "^/dev/sda2" | awk '{print $4}' | sed 's/.$//' | xargs)
timestamp=$(date +"%Y-%m-%d %T")
host_name=$(hostname -f)

# create the insert statement that will be used in the database
insert_stmt="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES ('$timestamp', (SELECT id FROM host_info WHERE hostname='$host_name'), '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"

# export the password run the insert statement in psql
export PGPASSWORD=$psql_password
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
exit $?
