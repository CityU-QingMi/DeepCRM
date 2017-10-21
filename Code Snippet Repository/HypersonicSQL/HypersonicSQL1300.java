        private Atom(String token) {
/**/
/**/
/**/
/**/
/**/
            if (token == null)
                throw new IllegalArgumentException("Tokens may not be null");
            if (token.length() < 1)
                throw new IllegalArgumentException("Tokens may not be empty");
            if (intPattern.matcher(token).matches()) {
                val = Long.parseLong(token);
                return;
            }
            if (token.length() == 1) {
                op = MathOp.valueOf(token.charAt(0));
                if (op != null) return;
            }
            // System.err.println("Trying '" + token + "'");
            val = deref(token);
        }
