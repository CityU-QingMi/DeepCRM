        @Override
        public Locale store(ActionInvocation invocation, Locale locale) {
            HttpServletResponse response = ServletActionContext.getResponse();

            Cookie cookie = new Cookie(attributeName, locale.toString());
            cookie.setMaxAge(1209600); // two weeks
            response.addCookie(cookie);

            return locale;
        }
