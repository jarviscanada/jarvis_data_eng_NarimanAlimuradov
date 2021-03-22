-- Nariman Alimuradov
-- ddl.sql will create the relations required to store the usage data and specs of the machine(s).

-- first we want to make sure we are using the 'host_agent' databsae
\c host_agent

-- create the 'host_info' table, which will store machine specifications
CREATE TABLE PUBLIC.host_info 
  ( 
     id               SERIAL NOT NULL PRIMARY KEY, 
     hostname         VARCHAR NOT NULL UNIQUE, 
     cpu_number       INTEGER NOT NULL, 
     cpu_architecture VARCHAR NOT NULL, 
     cpu_model        VARCHAR NOT NULL, 
     cpu_mhz          FLOAT NOT NULL, 
     L2_cache         INTEGER NOT NULL, 
     total_mem        INTEGER NOT NULL, 
     "timestamp"      TIMESTAMP NOT NULL 
  );


-- create the 'host_usage' table, which will periodically store resource usage data
CREATE TABLE PUBLIC.host_usage 
  ( 
     "timestamp"    TIMESTAMP NOT NULL, 
     host_id        SERIAL NOT NULL REFERENCES host_info (id) ON DELETE CASCADE, 
     memory_free    INTEGER NOT NULL,
     cpu_idle       INTEGER NOT NULL,
     cpu_kernel     INTEGER NOT NULL,
     disk_io        INTEGER NOT NULL,
     disk_available INTEGER NOT NULL
  );
