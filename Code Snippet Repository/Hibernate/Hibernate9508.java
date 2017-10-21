    private void withLimit(Consumer<Session> consumer) {
        rebuildSessionFactory( c -> c.addSqlFunction( "limit", LIMIT_FUNCTION ) );

        try {
            Session s = openSession();
            consumer.accept( s );
        } finally {
            // Rebuild to remove the function
            rebuildSessionFactory();
        }
    }
