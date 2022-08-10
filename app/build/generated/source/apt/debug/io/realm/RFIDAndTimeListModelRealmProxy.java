package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.OsObject;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RFIDAndTimeListModelRealmProxy extends com.zebra.rfid.Model.RFIDAndTimeListModel
    implements RealmObjectProxy, RFIDAndTimeListModelRealmProxyInterface {

    static final class RFIDAndTimeListModelColumnInfo extends ColumnInfo {
        long RfidIndex;
        long stationIdIndex;
        long TimeIndex;
        long isSelectedIndex;
        long isSyncedIndex;

        RFIDAndTimeListModelColumnInfo(SharedRealm realm, Table table) {
            super(5);
            this.RfidIndex = addColumnDetails(table, "Rfid", RealmFieldType.STRING);
            this.stationIdIndex = addColumnDetails(table, "stationId", RealmFieldType.INTEGER);
            this.TimeIndex = addColumnDetails(table, "Time", RealmFieldType.STRING);
            this.isSelectedIndex = addColumnDetails(table, "isSelected", RealmFieldType.BOOLEAN);
            this.isSyncedIndex = addColumnDetails(table, "isSynced", RealmFieldType.BOOLEAN);
        }

        RFIDAndTimeListModelColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new RFIDAndTimeListModelColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final RFIDAndTimeListModelColumnInfo src = (RFIDAndTimeListModelColumnInfo) rawSrc;
            final RFIDAndTimeListModelColumnInfo dst = (RFIDAndTimeListModelColumnInfo) rawDst;
            dst.RfidIndex = src.RfidIndex;
            dst.stationIdIndex = src.stationIdIndex;
            dst.TimeIndex = src.TimeIndex;
            dst.isSelectedIndex = src.isSelectedIndex;
            dst.isSyncedIndex = src.isSyncedIndex;
        }
    }

    private RFIDAndTimeListModelColumnInfo columnInfo;
    private ProxyState<com.zebra.rfid.Model.RFIDAndTimeListModel> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("Rfid");
        fieldNames.add("stationId");
        fieldNames.add("Time");
        fieldNames.add("isSelected");
        fieldNames.add("isSynced");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RFIDAndTimeListModelRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (RFIDAndTimeListModelColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.zebra.rfid.Model.RFIDAndTimeListModel>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$Rfid() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.RfidIndex);
    }

    @Override
    public void realmSet$Rfid(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'Rfid' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public Integer realmGet$stationId() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.stationIdIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.stationIdIndex);
    }

    @Override
    public void realmSet$stationId(Integer value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.stationIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.stationIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.stationIdIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.stationIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$Time() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.TimeIndex);
    }

    @Override
    public void realmSet$Time(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.TimeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.TimeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.TimeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.TimeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$isSelected() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isSelectedIndex);
    }

    @Override
    public void realmSet$isSelected(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isSelectedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isSelectedIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$isSynced() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isSyncedIndex);
    }

    @Override
    public void realmSet$isSynced(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isSyncedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isSyncedIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("RFIDAndTimeListModel")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("RFIDAndTimeListModel");
            realmObjectSchema.add("Rfid", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("stationId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("Time", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("isSelected", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("isSynced", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("RFIDAndTimeListModel");
    }

    public static RFIDAndTimeListModelColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_RFIDAndTimeListModel")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'RFIDAndTimeListModel' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_RFIDAndTimeListModel");
        final long columnCount = table.getColumnCount();
        if (columnCount != 5) {
            if (columnCount < 5) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 5 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 5 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 5 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final RFIDAndTimeListModelColumnInfo columnInfo = new RFIDAndTimeListModelColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'Rfid' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.RfidIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field Rfid");
            }
        }

        if (!columnTypes.containsKey("Rfid")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'Rfid' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("Rfid") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'Rfid' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.RfidIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'Rfid' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("Rfid"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'Rfid' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("stationId")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'stationId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("stationId") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'stationId' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.stationIdIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'stationId' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'stationId' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("Time")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'Time' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("Time") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'Time' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.TimeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'Time' is required. Either set @Required to field 'Time' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("isSelected")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isSelected' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("isSelected") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isSelected' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.isSelectedIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isSelected' does support null values in the existing Realm file. Use corresponding boxed type for field 'isSelected' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("isSynced")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isSynced' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("isSynced") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isSynced' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.isSyncedIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isSynced' does support null values in the existing Realm file. Use corresponding boxed type for field 'isSynced' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_RFIDAndTimeListModel";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.zebra.rfid.Model.RFIDAndTimeListModel createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.zebra.rfid.Model.RFIDAndTimeListModel obj = null;
        if (update) {
            Table table = realm.getTable(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("Rfid")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("Rfid"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.zebra.rfid.Model.RFIDAndTimeListModel.class), false, Collections.<String> emptyList());
                    obj = new io.realm.RFIDAndTimeListModelRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("Rfid")) {
                if (json.isNull("Rfid")) {
                    obj = (io.realm.RFIDAndTimeListModelRealmProxy) realm.createObjectInternal(com.zebra.rfid.Model.RFIDAndTimeListModel.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.RFIDAndTimeListModelRealmProxy) realm.createObjectInternal(com.zebra.rfid.Model.RFIDAndTimeListModel.class, json.getString("Rfid"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'Rfid'.");
            }
        }
        if (json.has("stationId")) {
            if (json.isNull("stationId")) {
                ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$stationId(null);
            } else {
                ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$stationId((int) json.getInt("stationId"));
            }
        }
        if (json.has("Time")) {
            if (json.isNull("Time")) {
                ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$Time(null);
            } else {
                ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$Time((String) json.getString("Time"));
            }
        }
        if (json.has("isSelected")) {
            if (json.isNull("isSelected")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isSelected' to null.");
            } else {
                ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$isSelected((boolean) json.getBoolean("isSelected"));
            }
        }
        if (json.has("isSynced")) {
            if (json.isNull("isSynced")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isSynced' to null.");
            } else {
                ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$isSynced((boolean) json.getBoolean("isSynced"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.zebra.rfid.Model.RFIDAndTimeListModel createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.zebra.rfid.Model.RFIDAndTimeListModel obj = new com.zebra.rfid.Model.RFIDAndTimeListModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("Rfid")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$Rfid(null);
                } else {
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$Rfid((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("stationId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$stationId(null);
                } else {
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$stationId((int) reader.nextInt());
                }
            } else if (name.equals("Time")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$Time(null);
                } else {
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$Time((String) reader.nextString());
                }
            } else if (name.equals("isSelected")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isSelected' to null.");
                } else {
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$isSelected((boolean) reader.nextBoolean());
                }
            } else if (name.equals("isSynced")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isSynced' to null.");
                } else {
                    ((RFIDAndTimeListModelRealmProxyInterface) obj).realmSet$isSynced((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'Rfid'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.zebra.rfid.Model.RFIDAndTimeListModel copyOrUpdate(Realm realm, com.zebra.rfid.Model.RFIDAndTimeListModel object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.zebra.rfid.Model.RFIDAndTimeListModel) cachedRealmObject;
        } else {
            com.zebra.rfid.Model.RFIDAndTimeListModel realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((RFIDAndTimeListModelRealmProxyInterface) object).realmGet$Rfid();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.zebra.rfid.Model.RFIDAndTimeListModel.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.RFIDAndTimeListModelRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.zebra.rfid.Model.RFIDAndTimeListModel copy(Realm realm, com.zebra.rfid.Model.RFIDAndTimeListModel newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.zebra.rfid.Model.RFIDAndTimeListModel) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.zebra.rfid.Model.RFIDAndTimeListModel realmObject = realm.createObjectInternal(com.zebra.rfid.Model.RFIDAndTimeListModel.class, ((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$Rfid(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$stationId(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$stationId());
            ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$Time(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$Time());
            ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$isSelected(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$isSelected());
            ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$isSynced(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$isSynced());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.zebra.rfid.Model.RFIDAndTimeListModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long tableNativePtr = table.getNativePtr();
        RFIDAndTimeListModelColumnInfo columnInfo = (RFIDAndTimeListModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RFIDAndTimeListModelRealmProxyInterface) object).realmGet$Rfid();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Number realmGet$stationId = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$stationId();
        if (realmGet$stationId != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.stationIdIndex, rowIndex, realmGet$stationId.longValue(), false);
        }
        String realmGet$Time = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$Time();
        if (realmGet$Time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.TimeIndex, rowIndex, realmGet$Time, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSelected(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSynced(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long tableNativePtr = table.getNativePtr();
        RFIDAndTimeListModelColumnInfo columnInfo = (RFIDAndTimeListModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.zebra.rfid.Model.RFIDAndTimeListModel object = null;
        while (objects.hasNext()) {
            object = (com.zebra.rfid.Model.RFIDAndTimeListModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RFIDAndTimeListModelRealmProxyInterface) object).realmGet$Rfid();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                Number realmGet$stationId = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$stationId();
                if (realmGet$stationId != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.stationIdIndex, rowIndex, realmGet$stationId.longValue(), false);
                }
                String realmGet$Time = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$Time();
                if (realmGet$Time != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.TimeIndex, rowIndex, realmGet$Time, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSelected(), false);
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSynced(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.zebra.rfid.Model.RFIDAndTimeListModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long tableNativePtr = table.getNativePtr();
        RFIDAndTimeListModelColumnInfo columnInfo = (RFIDAndTimeListModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RFIDAndTimeListModelRealmProxyInterface) object).realmGet$Rfid();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Number realmGet$stationId = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$stationId();
        if (realmGet$stationId != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.stationIdIndex, rowIndex, realmGet$stationId.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.stationIdIndex, rowIndex, false);
        }
        String realmGet$Time = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$Time();
        if (realmGet$Time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.TimeIndex, rowIndex, realmGet$Time, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.TimeIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSelected(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSynced(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long tableNativePtr = table.getNativePtr();
        RFIDAndTimeListModelColumnInfo columnInfo = (RFIDAndTimeListModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.RFIDAndTimeListModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.zebra.rfid.Model.RFIDAndTimeListModel object = null;
        while (objects.hasNext()) {
            object = (com.zebra.rfid.Model.RFIDAndTimeListModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RFIDAndTimeListModelRealmProxyInterface) object).realmGet$Rfid();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
                }
                cache.put(object, rowIndex);
                Number realmGet$stationId = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$stationId();
                if (realmGet$stationId != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.stationIdIndex, rowIndex, realmGet$stationId.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.stationIdIndex, rowIndex, false);
                }
                String realmGet$Time = ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$Time();
                if (realmGet$Time != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.TimeIndex, rowIndex, realmGet$Time, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.TimeIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSelected(), false);
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncedIndex, rowIndex, ((RFIDAndTimeListModelRealmProxyInterface)object).realmGet$isSynced(), false);
            }
        }
    }

    public static com.zebra.rfid.Model.RFIDAndTimeListModel createDetachedCopy(com.zebra.rfid.Model.RFIDAndTimeListModel realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.zebra.rfid.Model.RFIDAndTimeListModel unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.zebra.rfid.Model.RFIDAndTimeListModel)cachedObject.object;
            } else {
                unmanagedObject = (com.zebra.rfid.Model.RFIDAndTimeListModel)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.zebra.rfid.Model.RFIDAndTimeListModel();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((RFIDAndTimeListModelRealmProxyInterface) unmanagedObject).realmSet$Rfid(((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmGet$Rfid());
        ((RFIDAndTimeListModelRealmProxyInterface) unmanagedObject).realmSet$stationId(((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmGet$stationId());
        ((RFIDAndTimeListModelRealmProxyInterface) unmanagedObject).realmSet$Time(((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmGet$Time());
        ((RFIDAndTimeListModelRealmProxyInterface) unmanagedObject).realmSet$isSelected(((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmGet$isSelected());
        ((RFIDAndTimeListModelRealmProxyInterface) unmanagedObject).realmSet$isSynced(((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmGet$isSynced());
        return unmanagedObject;
    }

    static com.zebra.rfid.Model.RFIDAndTimeListModel update(Realm realm, com.zebra.rfid.Model.RFIDAndTimeListModel realmObject, com.zebra.rfid.Model.RFIDAndTimeListModel newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$stationId(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$stationId());
        ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$Time(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$Time());
        ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$isSelected(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$isSelected());
        ((RFIDAndTimeListModelRealmProxyInterface) realmObject).realmSet$isSynced(((RFIDAndTimeListModelRealmProxyInterface) newObject).realmGet$isSynced());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RFIDAndTimeListModel = proxy[");
        stringBuilder.append("{Rfid:");
        stringBuilder.append(realmGet$Rfid() != null ? realmGet$Rfid() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{stationId:");
        stringBuilder.append(realmGet$stationId() != null ? realmGet$stationId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{Time:");
        stringBuilder.append(realmGet$Time() != null ? realmGet$Time() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isSelected:");
        stringBuilder.append(realmGet$isSelected());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isSynced:");
        stringBuilder.append(realmGet$isSynced());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RFIDAndTimeListModelRealmProxy aRFIDAndTimeListModel = (RFIDAndTimeListModelRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aRFIDAndTimeListModel.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aRFIDAndTimeListModel.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aRFIDAndTimeListModel.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
