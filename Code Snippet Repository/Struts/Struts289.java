    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MockInterceptor)) {
            return false;
        }

        final MockInterceptor testInterceptor = (MockInterceptor) o;

        if (executed != testInterceptor.executed) {
            return false;
        }

        if ((expectedFoo != null) ? (!expectedFoo.equals(testInterceptor.expectedFoo)) : (testInterceptor.expectedFoo != null))
        {
            return false;
        }

        if ((foo != null) ? (!foo.equals(testInterceptor.foo)) : (testInterceptor.foo != null)) {
            return false;
        }

        return true;
    }
