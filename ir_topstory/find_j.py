import json

filepaths = ["2016_11","2016_10"]
for filepath in filepaths:
    f = open(filepath, encoding='utf-8')
    r = json.load(f)
    for doc in r["response"]["docs"]:

        url = doc["web_url"]
        date = doc["pub_date"]
        title = doc["headline"]["main"]
        content = doc["snippet"]
        if doc["byline"] != None and doc["byline"] != []:
            author = doc["byline"]["original"]
        else:
            author = "not apply"


        file = open('json_re.txt', 'a', encoding='utf-8')
        file.write(url)
        file.write('\n')
        file.write(title)
        file.write('\n')
        file.write(author)
        file.write('\n')
        file.write(date)
        file.write('\n')
        file.write(content)
        file.write('\n')


