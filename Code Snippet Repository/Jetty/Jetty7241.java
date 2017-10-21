        public String getString(String tag, boolean tags, boolean trim)
        {
            Node node = get(tag);
            if (node == null)
                return null;
            String s = node.toString(tags);
            if (s != null && trim)
                s = s.trim();
            return s;
        }
