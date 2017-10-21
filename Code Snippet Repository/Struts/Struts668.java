        public HttpParameters build() {
            Map<String, Parameter> parameters = (parent == null)
                    ? new HashMap<String, Parameter>()
                    : new HashMap<>(parent.parameters);

            for (Map.Entry<String, Object> entry : requestParameterMap.entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                parameters.put(name, new Parameter.Request(name, value));
            }

            return new HttpParameters(parameters);
        }
