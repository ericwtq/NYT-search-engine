import httplib2
import json
import time
def topstory_api(section):
	api_key = "ebdc5142b93843ccb93acc48553a2316"
	url = ('http://api.nytimes.com/svc/topstories/v2/%s.json?api-key=%s'%(section,api_key))
	h = httplib2.Http()
	response,content = h.request(url,"GET")
	result = json.loads(content)
	return result

section = ['home', 'arts', 'automobiles', 'books', 'business', 'fashion', 'food', 'health',
		   'insider', 'magazine', 'movies', 'national', 'nyregion', 'obituaries', 'opinion',
		   'politics', 'realestate', 'science', 'sports', 'sundayreview', 'technology', 'theater',
		   'tmagazine', 'travel', 'upshot', 'world']

for s in section:
	result = topstory_api(s)
	time.sleep(3)
	file_name = s + ".json"
	with open(file_name,'w') as file_object:
		json.dump(result,file_object)