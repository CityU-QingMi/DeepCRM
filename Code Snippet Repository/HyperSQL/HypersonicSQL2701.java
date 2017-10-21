    private void unmap(MappedByteBuffer buffer) throws IOException {

        if (buffer == null) {
            return;
        }

        try {
            Method cleanerMethod = buffer.getClass().getMethod("cleaner");

            cleanerMethod.setAccessible(true);

            Object cleaner     = cleanerMethod.invoke(buffer);
            Method clearMethod = cleaner.getClass().getMethod("clean");

            clearMethod.invoke(cleaner);
        } catch (InvocationTargetException e) {}
        catch (NoSuchMethodException e) {

            // Means we're not dealing with a Sun JVM?
        } catch (Throwable e) {}
    }
