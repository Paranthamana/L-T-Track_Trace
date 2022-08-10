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

public class StationModelRealmProxy extends com.zebra.rfid.Model.StationModel
    implements RealmObjectProxy, StationModelRealmProxyInterface {

    static final class StationModelColumnInfo extends ColumnInfo {
        long idIndex;
        long RFidIndex;
        long NameIndex;
        long ScanCountIndex;

        StationModelColumnInfo(SharedRealm realm, Table table) {
            super(4);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.RFidIndex = addColumnDetails(table, "RFid", RealmFieldType.STRING);
            this.NameIndex = addColumnDetails(table, "Name", RealmFieldType.STRING);
            this.ScanCountIndex = addColumnDetails(table, "ScanCount", RealmFieldType.INTEGER);
        }

        StationModelColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new StationModelColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final StationModelColumnInfo src = (StationModelColumnInfo) rawSrc;
            final StationModelColumnInfo dst = (StationModelColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.RFidIndex = src.RFidIndex;
            dst.NameIndex = src.NameIndex;
            dst.ScanCountIndex = src.ScanCountIndex;
        }
    }

    private StationModelColumnInfo columnInfo;
    private ProxyState<com.zebra.rfid.Model.StationModel> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("RFid");
        fieldNames.add("Name");
        fieldNames.add("ScanCount");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    StationModelRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (StationModelColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.zebra.rfid.Model.StationModel>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public Integer realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.idIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(Integer value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$RFid() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.RFidIndex);
    }

    @Override
    public void realmSet$RFid(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.RFidIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.RFidIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.RFidIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.RFidIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$Name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.NameIndex);
    }

    @Override
    public void realmSet$Name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.NameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.NameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.NameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.NameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Integer realmGet$ScanCount() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.ScanCountIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.ScanCountIndex);
    }

    @Override
    public void realmSet$ScanCount(Integer value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.ScanCountIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.ScanCountIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.ScanCountIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.ScanCountIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("StationModel")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("StationModel");
            realmObjectSchema.add("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("RFid", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("Name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("ScanCount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("StationModel");
    }

    public static StationModelColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_StationModel")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'StationModel' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_StationModel");
        final long columnCount = table.getColumnCount();
        if (columnCount != 4) {
            if (columnCount < 4) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final StationModelColumnInfo columnInfo = new StationModelColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.idIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field id");
            }
        }

        if (!columnTypes.containsKey("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("id") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'id' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.idIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("RFid")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'RFid' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("RFid") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'RFid' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.RFidIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'RFid' is required. Either set @Required to field 'RFid' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("Name")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'Name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("Name") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'Name' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.NameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'Name' is required. Either set @Required to field 'Name' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("ScanCount")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'ScanCount' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("ScanCount") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'ScanCount' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.ScanCountIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'ScanCount' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'ScanCount' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_StationModel";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.zebra.rfid.Model.StationModel createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.zebra.rfid.Model.StationModel obj = null;
        if (update) {
            Table table = realm.getTable(com.zebra.rfid.Model.StationModel.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.zebra.rfid.Model.StationModel.class), false, Collections.<String> emptyList());
                    obj = new io.realm.StationModelRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.StationModelRealmProxy) realm.createObjectInternal(com.zebra.rfid.Model.StationModel.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.StationModelRealmProxy) realm.createObjectInternal(com.zebra.rfid.Model.StationModel.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("RFid")) {
            if (json.isNull("RFid")) {
                ((StationModelRealmProxyInterface) obj).realmSet$RFid(null);
            } else {
                ((StationModelRealmProxyInterface) obj).realmSet$RFid((String) json.getString("RFid"));
            }
        }
        if (json.has("Name")) {
            if (json.isNull("Name")) {
                ((StationModelRealmProxyInterface) obj).realmSet$Name(null);
            } else {
                ((StationModelRealmProxyInterface) obj).realmSet$Name((String) json.getString("Name"));
            }
        }
        if (json.has("ScanCount")) {
            if (json.isNull("ScanCount")) {
                ((StationModelRealmProxyInterface) obj).realmSet$ScanCount(null);
            } else {
                ((StationModelRealmProxyInterface) obj).realmSet$ScanCount((int) json.getInt("ScanCount"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.zebra.rfid.Model.StationModel createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.zebra.rfid.Model.StationModel obj = new com.zebra.rfid.Model.StationModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((StationModelRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((StationModelRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("RFid")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((StationModelRealmProxyInterface) obj).realmSet$RFid(null);
                } else {
                    ((StationModelRealmProxyInterface) obj).realmSet$RFid((String) reader.nextString());
                }
            } else if (name.equals("Name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((StationModelRealmProxyInterface) obj).realmSet$Name(null);
                } else {
                    ((StationModelRealmProxyInterface) obj).realmSet$Name((String) reader.nextString());
                }
            } else if (name.equals("ScanCount")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((StationModelRealmProxyInterface) obj).realmSet$ScanCount(null);
                } else {
                    ((StationModelRealmProxyInterface) obj).realmSet$ScanCount((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.zebra.rfid.Model.StationModel copyOrUpdate(Realm realm, com.zebra.rfid.Model.StationModel object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.zebra.rfid.Model.StationModel) cachedRealmObject;
        } else {
            com.zebra.rfid.Model.StationModel realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.zebra.rfid.Model.StationModel.class);
                long pkColumnIndex = table.getPrimaryKey();
                Number value = ((StationModelRealmProxyInterface) object).realmGet$id();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstLong(pkColumnIndex, value.longValue());
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.zebra.rfid.Model.StationModel.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.StationModelRealmProxy();
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

    public static com.zebra.rfid.Model.StationModel copy(Realm realm, com.zebra.rfid.Model.StationModel newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.zebra.rfid.Model.StationModel) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.zebra.rfid.Model.StationModel realmObject = realm.createObjectInternal(com.zebra.rfid.Model.StationModel.class, ((StationModelRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((StationModelRealmProxyInterface) realmObject).realmSet$RFid(((StationModelRealmProxyInterface) newObject).realmGet$RFid());
            ((StationModelRealmProxyInterface) realmObject).realmSet$Name(((StationModelRealmProxyInterface) newObject).realmGet$Name());
            ((StationModelRealmProxyInterface) realmObject).realmSet$ScanCount(((StationModelRealmProxyInterface) newObject).realmGet$ScanCount());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.zebra.rfid.Model.StationModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.zebra.rfid.Model.StationModel.class);
        long tableNativePtr = table.getNativePtr();
        StationModelColumnInfo columnInfo = (StationModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.StationModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        Object primaryKeyValue = ((StationModelRealmProxyInterface) object).realmGet$id();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((StationModelRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, ((StationModelRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$RFid = ((StationModelRealmProxyInterface)object).realmGet$RFid();
        if (realmGet$RFid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.RFidIndex, rowIndex, realmGet$RFid, false);
        }
        String realmGet$Name = ((StationModelRealmProxyInterface)object).realmGet$Name();
        if (realmGet$Name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.NameIndex, rowIndex, realmGet$Name, false);
        }
        Number realmGet$ScanCount = ((StationModelRealmProxyInterface)object).realmGet$ScanCount();
        if (realmGet$ScanCount != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.ScanCountIndex, rowIndex, realmGet$ScanCount.longValue(), false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.zebra.rfid.Model.StationModel.class);
        long tableNativePtr = table.getNativePtr();
        StationModelColumnInfo columnInfo = (StationModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.StationModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.zebra.rfid.Model.StationModel object = null;
        while (objects.hasNext()) {
            object = (com.zebra.rfid.Model.StationModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                Object primaryKeyValue = ((StationModelRealmProxyInterface) object).realmGet$id();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((StationModelRealmProxyInterface) object).realmGet$id());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, ((StationModelRealmProxyInterface) object).realmGet$id());
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$RFid = ((StationModelRealmProxyInterface)object).realmGet$RFid();
                if (realmGet$RFid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.RFidIndex, rowIndex, realmGet$RFid, false);
                }
                String realmGet$Name = ((StationModelRealmProxyInterface)object).realmGet$Name();
                if (realmGet$Name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.NameIndex, rowIndex, realmGet$Name, false);
                }
                Number realmGet$ScanCount = ((StationModelRealmProxyInterface)object).realmGet$ScanCount();
                if (realmGet$ScanCount != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.ScanCountIndex, rowIndex, realmGet$ScanCount.longValue(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.zebra.rfid.Model.StationModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.zebra.rfid.Model.StationModel.class);
        long tableNativePtr = table.getNativePtr();
        StationModelColumnInfo columnInfo = (StationModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.StationModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        Object primaryKeyValue = ((StationModelRealmProxyInterface) object).realmGet$id();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((StationModelRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, ((StationModelRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        String realmGet$RFid = ((StationModelRealmProxyInterface)object).realmGet$RFid();
        if (realmGet$RFid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.RFidIndex, rowIndex, realmGet$RFid, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.RFidIndex, rowIndex, false);
        }
        String realmGet$Name = ((StationModelRealmProxyInterface)object).realmGet$Name();
        if (realmGet$Name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.NameIndex, rowIndex, realmGet$Name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.NameIndex, rowIndex, false);
        }
        Number realmGet$ScanCount = ((StationModelRealmProxyInterface)object).realmGet$ScanCount();
        if (realmGet$ScanCount != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.ScanCountIndex, rowIndex, realmGet$ScanCount.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ScanCountIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.zebra.rfid.Model.StationModel.class);
        long tableNativePtr = table.getNativePtr();
        StationModelColumnInfo columnInfo = (StationModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.StationModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.zebra.rfid.Model.StationModel object = null;
        while (objects.hasNext()) {
            object = (com.zebra.rfid.Model.StationModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                Object primaryKeyValue = ((StationModelRealmProxyInterface) object).realmGet$id();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((StationModelRealmProxyInterface) object).realmGet$id());
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, ((StationModelRealmProxyInterface) object).realmGet$id());
                }
                cache.put(object, rowIndex);
                String realmGet$RFid = ((StationModelRealmProxyInterface)object).realmGet$RFid();
                if (realmGet$RFid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.RFidIndex, rowIndex, realmGet$RFid, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.RFidIndex, rowIndex, false);
                }
                String realmGet$Name = ((StationModelRealmProxyInterface)object).realmGet$Name();
                if (realmGet$Name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.NameIndex, rowIndex, realmGet$Name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.NameIndex, rowIndex, false);
                }
                Number realmGet$ScanCount = ((StationModelRealmProxyInterface)object).realmGet$ScanCount();
                if (realmGet$ScanCount != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.ScanCountIndex, rowIndex, realmGet$ScanCount.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.ScanCountIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.zebra.rfid.Model.StationModel createDetachedCopy(com.zebra.rfid.Model.StationModel realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.zebra.rfid.Model.StationModel unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.zebra.rfid.Model.StationModel)cachedObject.object;
            } else {
                unmanagedObject = (com.zebra.rfid.Model.StationModel)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.zebra.rfid.Model.StationModel();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((StationModelRealmProxyInterface) unmanagedObject).realmSet$id(((StationModelRealmProxyInterface) realmObject).realmGet$id());
        ((StationModelRealmProxyInterface) unmanagedObject).realmSet$RFid(((StationModelRealmProxyInterface) realmObject).realmGet$RFid());
        ((StationModelRealmProxyInterface) unmanagedObject).realmSet$Name(((StationModelRealmProxyInterface) realmObject).realmGet$Name());
        ((StationModelRealmProxyInterface) unmanagedObject).realmSet$ScanCount(((StationModelRealmProxyInterface) realmObject).realmGet$ScanCount());
        return unmanagedObject;
    }

    static com.zebra.rfid.Model.StationModel update(Realm realm, com.zebra.rfid.Model.StationModel realmObject, com.zebra.rfid.Model.StationModel newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((StationModelRealmProxyInterface) realmObject).realmSet$RFid(((StationModelRealmProxyInterface) newObject).realmGet$RFid());
        ((StationModelRealmProxyInterface) realmObject).realmSet$Name(((StationModelRealmProxyInterface) newObject).realmGet$Name());
        ((StationModelRealmProxyInterface) realmObject).realmSet$ScanCount(((StationModelRealmProxyInterface) newObject).realmGet$ScanCount());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("StationModel = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{RFid:");
        stringBuilder.append(realmGet$RFid() != null ? realmGet$RFid() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{Name:");
        stringBuilder.append(realmGet$Name() != null ? realmGet$Name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ScanCount:");
        stringBuilder.append(realmGet$ScanCount() != null ? realmGet$ScanCount() : "null");
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
        StationModelRealmProxy aStationModel = (StationModelRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aStationModel.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aStationModel.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aStationModel.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
