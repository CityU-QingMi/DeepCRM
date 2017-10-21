        private List<Authentication.HeaderInfo> parseAuthenticateHeader(Response response, HttpHeader header)
        {
            // TODO: these should be ordered by strength
            List<Authentication.HeaderInfo> result = new ArrayList<>();
            List<String> values = Collections.list(response.getHeaders().getValues(header.asString()));
            for (String value : values)
            {
                Matcher matcher = AUTHENTICATE_PATTERN.matcher(value);
                if (matcher.matches())
                {
                    String type = matcher.group(1);
                    String realm = matcher.group(2);
                    String params = matcher.group(3);
                    Authentication.HeaderInfo headerInfo = new Authentication.HeaderInfo(type, realm, params, getAuthorizationHeader());
                    result.add(headerInfo);
                }
            }
            return result;
        }
