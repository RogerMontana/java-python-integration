def parse_invalid_json(jsonStr):
    import json, re
    # Substitue all the backslash from JSON string.
    jsonStr = re.sub(r'\\', '', jsonStr)
    try:
        return json.loads(jsonStr)
    except ValueError:
        while True:
            b = re.search(r'[\w|"]\s?(")\s?[\w|"]', jsonStr)
            if not b:
                break
            s, e = b.span(1)
            c = jsonStr[s:e]
            jsonStr = jsonStr[:s] + jsonStr[e:]
        return json.dumps(jsonStr)