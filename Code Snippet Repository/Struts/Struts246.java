    V internalCreate(K key) {
        try {
            FutureTask<V> futureTask = new FutureTask<>(new CallableCreate(key));

            // use a reference so we get the same equality semantics.
            Object keyReference = referenceKey(key);
            Future<V> future = futures.putIfAbsent(keyReference, futureTask);
            if (future == null) {
                // winning thread.
                try {
                    if (localFuture.get() != null) {
                        throw new IllegalStateException("Nested creations within the same cache are not allowed.");
                    }
                    localFuture.set(futureTask);
                    futureTask.run();
                    V value = futureTask.get();
                    putStrategy().execute(this, keyReference, referenceValue(keyReference, value));
                    return value;
                } finally {
                    localFuture.remove();
                    futures.remove(keyReference);
                }
            } else {
                // wait for winning thread.
                return future.get();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new RuntimeException(cause);
        }
    }
