class mypipline(object):
    def __init__(self):
        self.file = open('content.txt', 'a', encoding='utf-8')
    def process_item(self, item, spider):
        if len(item['content']) != 0:
            self.file.write(item['url_link'])
            self.file.write('\n')
            for it in item['title']:
                self.file.write(it)
            self.file.write(' ')
            for it in item['content']:
                self.file.write(it)
            self.file.write('\n')
    def open_spider(self, spider):
        pass
   
    def close_spider(self, spider):
        pass