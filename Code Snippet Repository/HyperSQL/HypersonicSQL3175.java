    Object[] getPrefixedMetadata(String key) {

        for (int i = 0; i < prefixes.size(); i++) {
            String prefix = (String) prefixes.get(i);

            if (key.startsWith(prefix)) {
                return (Object[]) meta.get(prefix);
            }
        }

        return null;
    }
