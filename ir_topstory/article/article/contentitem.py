import scrapy

class contentitem(scrapy.Item):
    title = scrapy.Field()
    content = scrapy.Field()
    url_link = scrapy.Field()