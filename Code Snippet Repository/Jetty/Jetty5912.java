        public OriginInfo (String n, Descriptor d)
        {
            name = n;
            descriptor = d;
            annotation=null;
            annotated=null;
            if (d == null)
                throw new IllegalArgumentException("No descriptor");
            if (d instanceof FragmentDescriptor)
                origin = Origin.WebFragment;
            else if (d instanceof OverrideDescriptor)
                origin =  Origin.WebOverride;
            else if (d instanceof DefaultsDescriptor)
                origin =  Origin.WebDefaults;
            else
                origin = Origin.WebXml;
        }
