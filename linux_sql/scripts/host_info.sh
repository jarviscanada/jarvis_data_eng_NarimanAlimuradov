#!/bin/bash
# Nariman Alimuradov

# first, we must get the db credentials from the user
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# important to check if correct number of arguments were given
if [ "$#" -ne 5 ]; then
    echo "Incorrect number of arguments given"
    exit 1
fi

# parse the machine's specifications
lscpu_out=`lscpu`
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(uname -m)
cpu_model=$(echo "$lscpu_out" | egrep "^Model\sname:" | awk '{$1=$2=""; print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU\sMHz:" | awk '{$1=$2=""; print $0}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2\scache:" | awk '{$1=$2=""; print $0}' | xargs)
total_mem=$(cat /proc/meminfo | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(date +"%Y-%m-%d %T") 

# insert statement that will be used to add to the database
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp) 
		VALUES ($hostname, $cpu_number, $cpu_architecture, $cpu_model, $cpu_mhz, $l2_cache, $total_mem, $timestamp);"

# save the password to the environment and execute the insert statement
export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c $insert_stmt
exit $?
