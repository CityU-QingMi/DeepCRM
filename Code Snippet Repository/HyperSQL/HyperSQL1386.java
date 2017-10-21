        public String toString() {

            StringBuffer sb = new StringBuffer(
                sdf.format(Long.valueOf(modTime * 1000L)) + ' ');

            sb.append((entryType == '\0') ? ' '
                                          : entryType);
            sb.append(ustar ? '*'
                            : ' ');
            sb.append(
                " "
                + StringUtil.toPaddedString(
                    Integer.toOctalString(fileMode), 4, ' ', false) + ' '
                        + StringUtil.toPaddedString(
                            Long.toString(dataSize), 11, ' ', false) + "  ");
            sb.append(StringUtil.toPaddedString(((ownerName == null) ? "-"
                                                                     : ownerName), 8,
                                                                     ' ',
                                                                     true));
            sb.append("  " + path);

            return sb.toString();
        }
