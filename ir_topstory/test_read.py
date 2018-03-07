import json
f = open("home.json", encoding='utf-8')
r = json.load(f)
a = []
for i in r["results"]:
    a.append(i["url"])
print(len(a))