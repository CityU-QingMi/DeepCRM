    private void closeQueryOutputStream() {
        if (pwQuery == null) return;

        try {
            if (htmlMode) {
                pwQuery.flush();
            }
        } finally {
            try {
                pwQuery.close();
            } finally {
                pwQuery = null; // Encourage GC of buffers
            }
        }
    }
