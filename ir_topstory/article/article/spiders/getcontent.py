import scrapy
from article.contentitem import contentitem
import json


class getcontent(scrapy.Spider):
    
    name = "getcontent"
    # sections = ['home', 'arts', 'automobiles', 'books', 'business', 'fashion', 'food', 'health',
	# 	   'insider', 'magazine', 'movies', 'national', 'nyregion', 'obituaries', 'opinion',
	# 	   'politics', 'realestate', 'science', 'sports', 'sundayreview', 'technology', 'theater',
	# 	   'tmagazine', 'travel', 'upshot', 'world']

    

    # for section in sections:
    #     filepath = "/Users/ericwtq/PycharmProjects/predata/ir_topstory/data/" + section + ".json"
    #     f = open(filepath, encoding='utf-8')
    #     r = json.load(f)
    #     for i in r["results"]:
    #         a.append(i["url"])
    a = []
    filepath = "/Users/ericwtq/PycharmProjects/predata/ir_topstory/2016_11"
    f = open(filepath, encoding='utf-8')
    r = json.load(f)
    for doc in r["response"]["docs"]:
        a.append(doc["web_url"])

    start_urls = a

    def parse(self, response):
        item = contentitem()
        item['title'] = response.css('title::text').extract()
        temps = response.css('.story-body-text.story-content::text').extract()
        item['content'] = temps
        item['url_link'] = response.url
        yield item
