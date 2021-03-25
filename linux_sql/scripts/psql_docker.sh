#!/bin/bash
# Nariman Alimuradov

# action can be either: create, start, or stop
# if create is chosen, must provide username and password as well
ACTION=$1
USERNAME=$2
PASSWORD=$3

# start docker if not started yet
systemctl status docker || systemctl start docker

# here we parse the first argument
case $ACTION in
	# creates a psql container 
	create)
	if [[ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ]]
	then
		echo "Error: Container already created"
		echo "Usage: ./psql_docker.sh create|start|stop [db_username][db_password]"
		exit 1
	fi
	
	# now we check if username and password are passed
	if [ "$#" -ne 3 ]
	then
		echo "Error: Please provide a username and password"
		echo "Usage: ./psql_docker.sh create|start|stop [db_username][db_password]"
		exit 1
	fi

	# if the above conditions are met, create a volume
	docker volume create pgdata
	
	# create the new container using the USERNAME and PASSWORD given
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=${PASSWORD} -e POSTGRES_USER=${USERNAME} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	# this exit will depend on the status of the previous line, failing if there is an error such as incorrect credentials
	exit $?
	;;
	
	# starts the container (must check to make sure container exists)
	start)
	if [[ $(docker container ls -a -f name=jrvs-psql | wc -l) -ne 2 ]]
	then
		echo "Error: Container does not exist"
		echo "Usage: ./psql_docker.sh create|start|stop [db_username][db_password]"
		exit 1
	fi
	docker container start jrvs-psql
	exit 0
	;;

	# stops the container (must check to make sure container exists)
	stop)
	if [[ $(docker container ls -a -f name=jrvs-psql | wc -l) -ne 2 ]]
	then
		echo "Error: Container does not exist"
		echo "Usage: ./psql_docker.sh create|start|stop [db_username][db_password]"
		exit 1
	fi
	docker container stop jrvs-psql
	exit 0
	;;

	# catch all in case an invalid ACTION is given
	*) 
	echo "Error: Incorrect argument given"
	echo "Usage: ./psql_docker.sh create|start|stop [db_username][db_password]"
	exit 1
	;;
esac

exit 0
