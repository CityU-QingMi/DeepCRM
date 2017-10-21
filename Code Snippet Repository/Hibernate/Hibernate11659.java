      private void recordReadWrite(int id, int before, int after, boolean failure, Ref<String> familyNameUpdate, Ref<Set<String>> familyMembersUpdate, Log<String> familyNameLog, Log<Set<String>> familyMembersLog) {
         log.tracef("Finished %s at %d", getClass().getSimpleName(), after);

         LogType readType, writeType;
         if (failure || rolledBack) {
            writeType = LogType.WRITE_FAILURE;
            readType = LogType.READ_FAILURE;
         } else {
            writeType = LogType.WRITE;
            readType = LogType.READ;
         }

         familyNameLog.setType(readType).setTimes(before, after);
         familyMembersLog.setType(readType).setTimes(before, after);

         getRecordList(familyNames, id).add(familyNameLog);
         getRecordList(familyMembers, id).add(familyMembersLog);


         if (familyNameLog.getValue() != null) {
            if (familyNameUpdate.isSet()) {
               getRecordList(familyNames, id).add(new Log<>(before, after, familyNameUpdate.get(), writeType, familyNameLog));
            }
            if (familyMembersUpdate.isSet()) {
               getRecordList(familyMembers, id).add(new Log<>(before, after, familyMembersUpdate.get(), writeType, familyMembersLog));
            }
         }
      }
