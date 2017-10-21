    private ThrowableProxy(final Throwable parent, final Stack<Class<?>> stack, final Map<String, CacheEntry> map,
                           final Throwable cause, final Set<Throwable> suppressedVisited,
                           final Set<Throwable> causeVisited) {
        causeVisited.add(cause);
        this.throwable = cause;
        this.name = cause.getClass().getName();
        this.message = this.throwable.getMessage();
        this.localizedMessage = this.throwable.getLocalizedMessage();
        this.extendedStackTrace = this.toExtendedStackTrace(stack, map, parent.getStackTrace(), cause.getStackTrace());
        final Throwable causeCause = cause.getCause();
        this.causeProxy = causeCause == null || causeVisited.contains(causeCause) ? null : new ThrowableProxy(parent,
            stack, map, causeCause, suppressedVisited, causeVisited);
        this.suppressedProxies = this.toSuppressedProxies(cause, suppressedVisited);
    }
