    public void toObject(ActionInvocation invocation, Reader in, Object target) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            sb.append(buffer, 0, len);
        }
        if (target != null && sb.length() > 0 && sb.charAt(0) == '[') {
            JSONArray jsonArray = JSONArray.fromObject(sb.toString());
            if (target.getClass().isArray()) {
                JSONArray.toArray(jsonArray, target, new JsonConfig());
            } else {
                JSONArray.toList(jsonArray, target, new JsonConfig());
            }

        } else {
            JSONObject jsonObject = JSONObject.fromObject(sb.toString());
            JSONObject.toBean(jsonObject, target, new JsonConfig());
        }
    }
