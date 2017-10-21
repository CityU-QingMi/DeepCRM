    protected void map(Map map, Method method) throws JSONException {
        this.add("{");

        Iterator it = map.entrySet().iterator();

        boolean warnedNonString = false; // one report per map
        boolean hasData = false;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (excludeNullProperties && entry.getValue() == null) {
                continue;
            }

            Object key = entry.getKey();
            if (key == null) {
                LOG.error("Cannot build expression for null key in {}", exprStack);
                continue;
            }

            String expr = null;
            if (this.buildExpr) {
                expr = this.expandExpr(key.toString());
                if (this.shouldExcludeProperty(expr)) {
                    continue;
                }
                expr = this.setExprStack(expr);
            }
            if (hasData) {
                this.add(',');
            }
            hasData = true;
            if (!warnedNonString && !(key instanceof String)) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("JavaScript doesn't support non-String keys, using toString() on {}", key.getClass().getName());
                }
                warnedNonString = true;
            }
            this.value(key.toString(), method);
            this.add(":");
            this.value(entry.getValue(), method);
            if (this.buildExpr) {
                this.setExprStack(expr);
            }
        }

        this.add("}");
    }
