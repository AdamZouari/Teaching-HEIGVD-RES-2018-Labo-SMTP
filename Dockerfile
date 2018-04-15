FROM java:8
MAINTAINER Adam Zouari <adam.zouari@heig-vd.ch>

#
# When we build the image, we retrieve the latest executable jar directly in the MockMock github repo
#
ADD ["https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true", "/opt/res/"]

# Entrypoint to allow the user to choose the ports smtp and http at the run of the container
ENTRYPOINT ["java", "-jar", "/opt/res/MockMock.jar"]
