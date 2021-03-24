# Linux Cluster Monitoring Agent

Multi-machine networks can be difficult

## How do I use it?

Liek this.

# Implementation
**Bash scripts** are used to collect hardware specifications as well as resource usage.
These values are then stored in a **PostgreSQL** database provisioned using **Docker**. The resource usage is scheduled using a **Crontab** file. Requested information is then pulled from the database using  **SQL** queries.

## Architecture
A **client-server** architecture is used. Each client computer will have a copy of the script, while the server computer will also store the database.

## What do the scripts do?
This project consists of five key scripts:

**psql_docker.sh**
> Shell script that is used to create

Usage: ```bash psql_docker.sh create|start|stop [db_username] [db_password]```
<br/><br/>
**host_info.sh**
> Shell script that will fetch the **hardware specifications** of the machine and insert it into the database.

Usage: ```bash psql_docker.sh create|start|stop [db_username] [db_password]```
<br/><br/>
**host_usage.sh**
> Shell script that will fetch the **resource usage** of the machine and insert it into the database.

<br/><br/>
**ddl.sql**
> Creates the 

<br/><br/>
**queries.sql**
> Creates the 


## Database Modeling

# Results and Next Steps

## Test Examples

## Improvements
While the monitoring agent has many fantastic uses currently, there are a few improvements that would be welcome additions:

**1. Wider selection of resource usage data**

Having a larger. While, this may slow down the gathering of the data as well as introduce more complexity for the user, the benefits

**2. ss**

**3. as**

