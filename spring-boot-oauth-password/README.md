Run :
curl -X POST -vu client:secret http://localhost:9998/oauth/token -H "Accept: application/json" -d "password=password&username=user&grant_type=password&scope=read%20write&client_secret=secret&client_id=client"
