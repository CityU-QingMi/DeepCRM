    public RegexPatternMatcherExpression compilePattern(String data) {
        Map<Integer, String> params = new HashMap<>();

        Matcher matcher = PATTERN.matcher(data);
        int count = 0;
        while (matcher.find()) {
            String expression = matcher.group(1);
            //check if it is a regex
            int index = expression.indexOf(':');
            if (index > 0) {
                String paramName = expression.substring(0, index);
                String regex = StringUtils.substring(expression, index + 1);
                if (StringUtils.isBlank(regex)) {
                    throw new IllegalArgumentException("invalid expression [" + expression + "], named parameter regular exression "
                            + "must be in the format {PARAM_NAME:REGEX}");
                }

                params.put(++count, paramName);

            } else {
                params.put(++count, expression);
            }
        }

        //generate a new pattern used to match URIs
        //replace {X:B} by (B)
        String newPattern = data.replaceAll("(\\{[^\\}]*?:(.*?)\\})", "($2)");

        //replace {X} by (.*?)
        newPattern = newPattern.replaceAll("(\\{.*?\\})", "(.*?)");
        return new RegexPatternMatcherExpression(Pattern.compile(newPattern), params);
    }
