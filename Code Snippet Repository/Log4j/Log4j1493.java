            public Text apply(String plainText, List<IStyle> styles) {
                if (plainText.length() == 0) { return new Text(0); }
                Text result = new Text(plainText.length());
                IStyle[] all = styles.toArray(new IStyle[styles.size()]);
                result.indexToStyle.put(result.plain.length(), Style.on(all));
                result.plain.append(plainText);
                result.length = result.plain.length();
                reverse(all);
                result.indexToStyle.put(result.plain.length(), Style.off(all) + Style.reset.off());
                return result;
            }
