#!/bin/bash

# if passing arguments
if [[ "$1" != "" && "$2" != "" ]]
then
	# run docker server with specified ports
	docker run -d -p $1:$1 -p $2:$2 adamzouari/mockmockservermail:latest -p $1 -h $2
else
	# else run with the defaults ports
	docker run -d -p 25:25 -p 8282:8282 adamzouari/mockmockservermail:latest 
fi
