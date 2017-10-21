    public String fromObject(ActionInvocation invocation, Object obj, String resultCode, Writer stream) throws IOException {
        if (obj != null) {
            if (isArray(obj)) {
                JSONArray jsonArray = JSONArray.fromObject(obj);
                stream.write(jsonArray.toString());
            } else {
                JSONObject jsonObject = JSONObject.fromObject(obj);
                stream.write(jsonObject.toString());
            }
        }
        return null;


    }
