        public Artifact (String csv)
        {
            if (csv != null && !"".equals(csv))
            {
                String[] atoms = StringUtil.csvSplit(csv);
                if (atoms.length >= 3)
                {
                    gid = atoms[0].trim();
                    aid = atoms[1].trim();
                    path = atoms[2].trim();
                }
            }
        }
