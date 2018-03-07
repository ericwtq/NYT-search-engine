#ebdc5142b93843ccb93acc48553a2316
import httplib2
import json
import time

def article_api(year,month):
    api_key = "ebdc5142b93843ccb93acc48553a2316"
    url = 'https://api.nytimes.com/svc/archive/v1/%s/%s.json?api-key=%s'%(year,month,api_key)
    h = httplib2.Http()
    response, content = h.request(url, "GET")
    result = json.loads(content)
    return result

result = article_api(2016,11)


file_name = "2016_11"
with open(file_name, 'w') as file_object:
    json.dump(result, file_object)