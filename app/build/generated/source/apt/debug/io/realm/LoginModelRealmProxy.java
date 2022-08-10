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

public class LoginModelRealmProxy extends com.zebra.rfid.Model.LoginModel
    implements RealmObjectProxy, LoginModelRealmProxyInterface {

    static final class LoginModelColumnInfo extends ColumnInfo {
        long PasswordIndex;
        long AccessTokenIndex;
        long StationsIndex;
        long userNameIndex;
        long loginModelRealmListIndex;

        LoginModelColumnInfo(SharedRealm realm, Table table) {
            super(5);
            this.PasswordIndex = addColumnDetails(table, "Password", RealmFieldType.STRING);
            this.AccessTokenIndex = addColumnDetails(table, "AccessToken", RealmFieldType.STRING);
            this.StationsIndex = addColumnDetails(table, "Stations", RealmFieldType.STRING);
            this.userNameIndex = addColumnDetails(table, "userName", RealmFieldType.STRING);
            this.loginModelRealmListIndex = addColumnDetails(table, "loginModelRealmList", RealmFieldType.LIST);
        }

        LoginModelColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new LoginModelColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final LoginModelColumnInfo src = (LoginModelColumnInfo) rawSrc;
            final LoginModelColumnInfo dst = (LoginModelColumnInfo) rawDst;
            dst.PasswordIndex = src.PasswordIndex;
            dst.AccessTokenIndex = src.AccessTokenIndex;
            dst.StationsIndex = src.StationsIndex;
            dst.userNameIndex = src.userNameIndex;
            dst.loginModelRealmListIndex = src.loginModelRealmListIndex;
        }
    }

    private LoginModelColumnInfo columnInfo;
    private ProxyState<com.zebra.rfid.Model.LoginModel> proxyState;
    private RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("Password");
        fieldNames.add("AccessToken");
        fieldNames.add("Stations");
        fieldNames.add("userName");
        fieldNames.add("loginModelRealmList");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    LoginModelRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (LoginModelColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.zebra.rfid.Model.LoginModel>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$Password() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.PasswordIndex);
    }

    @Override
    public void realmSet$Password(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.PasswordIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.PasswordIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.PasswordIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.PasswordIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$AccessToken() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.AccessTokenIndex);
    }

    @Override
    public void realmSet$AccessToken(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.AccessTokenIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.AccessTokenIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.AccessTokenIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.AccessTokenIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$Stations() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.StationsIndex);
    }

    @Override
    public void realmSet$Stations(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.StationsIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.StationsIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.StationsIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.StationsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$userName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.userNameIndex);
    }

    @Override
    public void realmSet$userName(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'userName' cannot be changed after object was created.");
    }

    @Override
    public RealmList<com.zebra.rfid.Model.LoginModel> realmGet$loginModelRealmList() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (loginModelRealmListRealmList != null) {
            return loginModelRealmListRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.loginModelRealmListIndex);
            loginModelRealmListRealmList = new RealmList<com.zebra.rfid.Model.LoginModel>(com.zebra.rfid.Model.LoginModel.class, linkView, proxyState.getRealm$realm());
            return loginModelRealmListRealmList;
        }
    }

    @Override
    public void realmSet$loginModelRealmList(RealmList<com.zebra.rfid.Model.LoginModel> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("loginModelRealmList")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.zebra.rfid.Model.LoginModel> original = value;
                value = new RealmList<com.zebra.rfid.Model.LoginModel>();
                for (com.zebra.rfid.Model.LoginModel item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.loginModelRealmListIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!(RealmObject.isManaged(linkedObject) && RealmObject.isValid(linkedObject))) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("LoginModel")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("LoginModel");
            realmObjectSchema.add("Password", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("AccessToken", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("Stations", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("userName", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            if (!realmSchema.contains("LoginModel")) {
                LoginModelRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("loginModelRealmList", RealmFieldType.LIST, realmSchema.get("LoginModel"));
            return realmObjectSchema;
        }
        return realmSchema.get("LoginModel");
    }

    public static LoginModelColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_LoginModel")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'LoginModel' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_LoginModel");
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

        final LoginModelColumnInfo columnInfo = new LoginModelColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'userName' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.userNameIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field userName");
            }
        }

        if (!columnTypes.containsKey("Password")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'Password' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("Password") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'Password' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.PasswordIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'Password' is required. Either set @Required to field 'Password' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("AccessToken")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'AccessToken' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("AccessToken") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'AccessToken' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.AccessTokenIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'AccessToken' is required. Either set @Required to field 'AccessToken' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("Stations")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'Stations' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("Stations") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'Stations' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.StationsIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'Stations' is required. Either set @Required to field 'Stations' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("userName")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'userName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("userName") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'userName' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.userNameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'userName' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("userName"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'userName' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("loginModelRealmList")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'loginModelRealmList'");
        }
        if (columnTypes.get("loginModelRealmList") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'LoginModel' for field 'loginModelRealmList'");
        }
        if (!sharedRealm.hasTable("class_LoginModel")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_LoginModel' for field 'loginModelRealmList'");
        }
        Table table_4 = sharedRealm.getTable("class_LoginModel");
        if (!table.getLinkTarget(columnInfo.loginModelRealmListIndex).hasSameSchema(table_4)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'loginModelRealmList': '" + table.getLinkTarget(columnInfo.loginModelRealmListIndex).getName() + "' expected - was '" + table_4.getName() + "'");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_LoginModel";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.zebra.rfid.Model.LoginModel createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.zebra.rfid.Model.LoginModel obj = null;
        if (update) {
            Table table = realm.getTable(com.zebra.rfid.Model.LoginModel.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("userName")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("userName"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.zebra.rfid.Model.LoginModel.class), false, Collections.<String> emptyList());
                    obj = new io.realm.LoginModelRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("loginModelRealmList")) {
                excludeFields.add("loginModelRealmList");
            }
            if (json.has("userName")) {
                if (json.isNull("userName")) {
                    obj = (io.realm.LoginModelRealmProxy) realm.createObjectInternal(com.zebra.rfid.Model.LoginModel.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.LoginModelRealmProxy) realm.createObjectInternal(com.zebra.rfid.Model.LoginModel.class, json.getString("userName"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'userName'.");
            }
        }
        if (json.has("Password")) {
            if (json.isNull("Password")) {
                ((LoginModelRealmProxyInterface) obj).realmSet$Password(null);
            } else {
                ((LoginModelRealmProxyInterface) obj).realmSet$Password((String) json.getString("Password"));
            }
        }
        if (json.has("AccessToken")) {
            if (json.isNull("AccessToken")) {
                ((LoginModelRealmProxyInterface) obj).realmSet$AccessToken(null);
            } else {
                ((LoginModelRealmProxyInterface) obj).realmSet$AccessToken((String) json.getString("AccessToken"));
            }
        }
        if (json.has("Stations")) {
            if (json.isNull("Stations")) {
                ((LoginModelRealmProxyInterface) obj).realmSet$Stations(null);
            } else {
                ((LoginModelRealmProxyInterface) obj).realmSet$Stations((String) json.getString("Stations"));
            }
        }
        if (json.has("loginModelRealmList")) {
            if (json.isNull("loginModelRealmList")) {
                ((LoginModelRealmProxyInterface) obj).realmSet$loginModelRealmList(null);
            } else {
                ((LoginModelRealmProxyInterface) obj).realmGet$loginModelRealmList().clear();
                JSONArray array = json.getJSONArray("loginModelRealmList");
                for (int i = 0; i < array.length(); i++) {
                    com.zebra.rfid.Model.LoginModel item = LoginModelRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((LoginModelRealmProxyInterface) obj).realmGet$loginModelRealmList().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.zebra.rfid.Model.LoginModel createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.zebra.rfid.Model.LoginModel obj = new com.zebra.rfid.Model.LoginModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("Password")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginModelRealmProxyInterface) obj).realmSet$Password(null);
                } else {
                    ((LoginModelRealmProxyInterface) obj).realmSet$Password((String) reader.nextString());
                }
            } else if (name.equals("AccessToken")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginModelRealmProxyInterface) obj).realmSet$AccessToken(null);
                } else {
                    ((LoginModelRealmProxyInterface) obj).realmSet$AccessToken((String) reader.nextString());
                }
            } else if (name.equals("Stations")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginModelRealmProxyInterface) obj).realmSet$Stations(null);
                } else {
                    ((LoginModelRealmProxyInterface) obj).realmSet$Stations((String) reader.nextString());
                }
            } else if (name.equals("userName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginModelRealmProxyInterface) obj).realmSet$userName(null);
                } else {
                    ((LoginModelRealmProxyInterface) obj).realmSet$userName((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("loginModelRealmList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginModelRealmProxyInterface) obj).realmSet$loginModelRealmList(null);
                } else {
                    ((LoginModelRealmProxyInterface) obj).realmSet$loginModelRealmList(new RealmList<com.zebra.rfid.Model.LoginModel>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.zebra.rfid.Model.LoginModel item = LoginModelRealmProxy.createUsingJsonStream(realm, reader);
                        ((LoginModelRealmProxyInterface) obj).realmGet$loginModelRealmList().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'userName'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.zebra.rfid.Model.LoginModel copyOrUpdate(Realm realm, com.zebra.rfid.Model.LoginModel object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.zebra.rfid.Model.LoginModel) cachedRealmObject;
        } else {
            com.zebra.rfid.Model.LoginModel realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.zebra.rfid.Model.LoginModel.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((LoginModelRealmProxyInterface) object).realmGet$userName();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.zebra.rfid.Model.LoginModel.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.LoginModelRealmProxy();
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

    public static com.zebra.rfid.Model.LoginModel copy(Realm realm, com.zebra.rfid.Model.LoginModel newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.zebra.rfid.Model.LoginModel) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.zebra.rfid.Model.LoginModel realmObject = realm.createObjectInternal(com.zebra.rfid.Model.LoginModel.class, ((LoginModelRealmProxyInterface) newObject).realmGet$userName(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((LoginModelRealmProxyInterface) realmObject).realmSet$Password(((LoginModelRealmProxyInterface) newObject).realmGet$Password());
            ((LoginModelRealmProxyInterface) realmObject).realmSet$AccessToken(((LoginModelRealmProxyInterface) newObject).realmGet$AccessToken());
            ((LoginModelRealmProxyInterface) realmObject).realmSet$Stations(((LoginModelRealmProxyInterface) newObject).realmGet$Stations());

            RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListList = ((LoginModelRealmProxyInterface) newObject).realmGet$loginModelRealmList();
            if (loginModelRealmListList != null) {
                RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListRealmList = ((LoginModelRealmProxyInterface) realmObject).realmGet$loginModelRealmList();
                for (int i = 0; i < loginModelRealmListList.size(); i++) {
                    com.zebra.rfid.Model.LoginModel loginModelRealmListItem = loginModelRealmListList.get(i);
                    com.zebra.rfid.Model.LoginModel cacheloginModelRealmList = (com.zebra.rfid.Model.LoginModel) cache.get(loginModelRealmListItem);
                    if (cacheloginModelRealmList != null) {
                        loginModelRealmListRealmList.add(cacheloginModelRealmList);
                    } else {
                        loginModelRealmListRealmList.add(LoginModelRealmProxy.copyOrUpdate(realm, loginModelRealmListList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.zebra.rfid.Model.LoginModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.zebra.rfid.Model.LoginModel.class);
        long tableNativePtr = table.getNativePtr();
        LoginModelColumnInfo columnInfo = (LoginModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.LoginModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((LoginModelRealmProxyInterface) object).realmGet$userName();
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
        String realmGet$Password = ((LoginModelRealmProxyInterface)object).realmGet$Password();
        if (realmGet$Password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.PasswordIndex, rowIndex, realmGet$Password, false);
        }
        String realmGet$AccessToken = ((LoginModelRealmProxyInterface)object).realmGet$AccessToken();
        if (realmGet$AccessToken != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.AccessTokenIndex, rowIndex, realmGet$AccessToken, false);
        }
        String realmGet$Stations = ((LoginModelRealmProxyInterface)object).realmGet$Stations();
        if (realmGet$Stations != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.StationsIndex, rowIndex, realmGet$Stations, false);
        }

        RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListList = ((LoginModelRealmProxyInterface) object).realmGet$loginModelRealmList();
        if (loginModelRealmListList != null) {
            long loginModelRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.loginModelRealmListIndex, rowIndex);
            for (com.zebra.rfid.Model.LoginModel loginModelRealmListItem : loginModelRealmListList) {
                Long cacheItemIndexloginModelRealmList = cache.get(loginModelRealmListItem);
                if (cacheItemIndexloginModelRealmList == null) {
                    cacheItemIndexloginModelRealmList = LoginModelRealmProxy.insert(realm, loginModelRealmListItem, cache);
                }
                LinkView.nativeAdd(loginModelRealmListNativeLinkViewPtr, cacheItemIndexloginModelRealmList);
            }
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.zebra.rfid.Model.LoginModel.class);
        long tableNativePtr = table.getNativePtr();
        LoginModelColumnInfo columnInfo = (LoginModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.LoginModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.zebra.rfid.Model.LoginModel object = null;
        while (objects.hasNext()) {
            object = (com.zebra.rfid.Model.LoginModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((LoginModelRealmProxyInterface) object).realmGet$userName();
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
                String realmGet$Password = ((LoginModelRealmProxyInterface)object).realmGet$Password();
                if (realmGet$Password != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.PasswordIndex, rowIndex, realmGet$Password, false);
                }
                String realmGet$AccessToken = ((LoginModelRealmProxyInterface)object).realmGet$AccessToken();
                if (realmGet$AccessToken != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.AccessTokenIndex, rowIndex, realmGet$AccessToken, false);
                }
                String realmGet$Stations = ((LoginModelRealmProxyInterface)object).realmGet$Stations();
                if (realmGet$Stations != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.StationsIndex, rowIndex, realmGet$Stations, false);
                }

                RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListList = ((LoginModelRealmProxyInterface) object).realmGet$loginModelRealmList();
                if (loginModelRealmListList != null) {
                    long loginModelRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.loginModelRealmListIndex, rowIndex);
                    for (com.zebra.rfid.Model.LoginModel loginModelRealmListItem : loginModelRealmListList) {
                        Long cacheItemIndexloginModelRealmList = cache.get(loginModelRealmListItem);
                        if (cacheItemIndexloginModelRealmList == null) {
                            cacheItemIndexloginModelRealmList = LoginModelRealmProxy.insert(realm, loginModelRealmListItem, cache);
                        }
                        LinkView.nativeAdd(loginModelRealmListNativeLinkViewPtr, cacheItemIndexloginModelRealmList);
                    }
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.zebra.rfid.Model.LoginModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.zebra.rfid.Model.LoginModel.class);
        long tableNativePtr = table.getNativePtr();
        LoginModelColumnInfo columnInfo = (LoginModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.LoginModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((LoginModelRealmProxyInterface) object).realmGet$userName();
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
        String realmGet$Password = ((LoginModelRealmProxyInterface)object).realmGet$Password();
        if (realmGet$Password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.PasswordIndex, rowIndex, realmGet$Password, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.PasswordIndex, rowIndex, false);
        }
        String realmGet$AccessToken = ((LoginModelRealmProxyInterface)object).realmGet$AccessToken();
        if (realmGet$AccessToken != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.AccessTokenIndex, rowIndex, realmGet$AccessToken, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.AccessTokenIndex, rowIndex, false);
        }
        String realmGet$Stations = ((LoginModelRealmProxyInterface)object).realmGet$Stations();
        if (realmGet$Stations != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.StationsIndex, rowIndex, realmGet$Stations, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.StationsIndex, rowIndex, false);
        }

        long loginModelRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.loginModelRealmListIndex, rowIndex);
        LinkView.nativeClear(loginModelRealmListNativeLinkViewPtr);
        RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListList = ((LoginModelRealmProxyInterface) object).realmGet$loginModelRealmList();
        if (loginModelRealmListList != null) {
            for (com.zebra.rfid.Model.LoginModel loginModelRealmListItem : loginModelRealmListList) {
                Long cacheItemIndexloginModelRealmList = cache.get(loginModelRealmListItem);
                if (cacheItemIndexloginModelRealmList == null) {
                    cacheItemIndexloginModelRealmList = LoginModelRealmProxy.insertOrUpdate(realm, loginModelRealmListItem, cache);
                }
                LinkView.nativeAdd(loginModelRealmListNativeLinkViewPtr, cacheItemIndexloginModelRealmList);
            }
        }

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.zebra.rfid.Model.LoginModel.class);
        long tableNativePtr = table.getNativePtr();
        LoginModelColumnInfo columnInfo = (LoginModelColumnInfo) realm.schema.getColumnInfo(com.zebra.rfid.Model.LoginModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.zebra.rfid.Model.LoginModel object = null;
        while (objects.hasNext()) {
            object = (com.zebra.rfid.Model.LoginModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((LoginModelRealmProxyInterface) object).realmGet$userName();
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
                String realmGet$Password = ((LoginModelRealmProxyInterface)object).realmGet$Password();
                if (realmGet$Password != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.PasswordIndex, rowIndex, realmGet$Password, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.PasswordIndex, rowIndex, false);
                }
                String realmGet$AccessToken = ((LoginModelRealmProxyInterface)object).realmGet$AccessToken();
                if (realmGet$AccessToken != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.AccessTokenIndex, rowIndex, realmGet$AccessToken, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.AccessTokenIndex, rowIndex, false);
                }
                String realmGet$Stations = ((LoginModelRealmProxyInterface)object).realmGet$Stations();
                if (realmGet$Stations != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.StationsIndex, rowIndex, realmGet$Stations, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.StationsIndex, rowIndex, false);
                }

                long loginModelRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.loginModelRealmListIndex, rowIndex);
                LinkView.nativeClear(loginModelRealmListNativeLinkViewPtr);
                RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListList = ((LoginModelRealmProxyInterface) object).realmGet$loginModelRealmList();
                if (loginModelRealmListList != null) {
                    for (com.zebra.rfid.Model.LoginModel loginModelRealmListItem : loginModelRealmListList) {
                        Long cacheItemIndexloginModelRealmList = cache.get(loginModelRealmListItem);
                        if (cacheItemIndexloginModelRealmList == null) {
                            cacheItemIndexloginModelRealmList = LoginModelRealmProxy.insertOrUpdate(realm, loginModelRealmListItem, cache);
                        }
                        LinkView.nativeAdd(loginModelRealmListNativeLinkViewPtr, cacheItemIndexloginModelRealmList);
                    }
                }

            }
        }
    }

    public static com.zebra.rfid.Model.LoginModel createDetachedCopy(com.zebra.rfid.Model.LoginModel realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.zebra.rfid.Model.LoginModel unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.zebra.rfid.Model.LoginModel)cachedObject.object;
            } else {
                unmanagedObject = (com.zebra.rfid.Model.LoginModel)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.zebra.rfid.Model.LoginModel();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((LoginModelRealmProxyInterface) unmanagedObject).realmSet$Password(((LoginModelRealmProxyInterface) realmObject).realmGet$Password());
        ((LoginModelRealmProxyInterface) unmanagedObject).realmSet$AccessToken(((LoginModelRealmProxyInterface) realmObject).realmGet$AccessToken());
        ((LoginModelRealmProxyInterface) unmanagedObject).realmSet$Stations(((LoginModelRealmProxyInterface) realmObject).realmGet$Stations());
        ((LoginModelRealmProxyInterface) unmanagedObject).realmSet$userName(((LoginModelRealmProxyInterface) realmObject).realmGet$userName());

        // Deep copy of loginModelRealmList
        if (currentDepth == maxDepth) {
            ((LoginModelRealmProxyInterface) unmanagedObject).realmSet$loginModelRealmList(null);
        } else {
            RealmList<com.zebra.rfid.Model.LoginModel> managedloginModelRealmListList = ((LoginModelRealmProxyInterface) realmObject).realmGet$loginModelRealmList();
            RealmList<com.zebra.rfid.Model.LoginModel> unmanagedloginModelRealmListList = new RealmList<com.zebra.rfid.Model.LoginModel>();
            ((LoginModelRealmProxyInterface) unmanagedObject).realmSet$loginModelRealmList(unmanagedloginModelRealmListList);
            int nextDepth = currentDepth + 1;
            int size = managedloginModelRealmListList.size();
            for (int i = 0; i < size; i++) {
                com.zebra.rfid.Model.LoginModel item = LoginModelRealmProxy.createDetachedCopy(managedloginModelRealmListList.get(i), nextDepth, maxDepth, cache);
                unmanagedloginModelRealmListList.add(item);
            }
        }
        return unmanagedObject;
    }

    static com.zebra.rfid.Model.LoginModel update(Realm realm, com.zebra.rfid.Model.LoginModel realmObject, com.zebra.rfid.Model.LoginModel newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((LoginModelRealmProxyInterface) realmObject).realmSet$Password(((LoginModelRealmProxyInterface) newObject).realmGet$Password());
        ((LoginModelRealmProxyInterface) realmObject).realmSet$AccessToken(((LoginModelRealmProxyInterface) newObject).realmGet$AccessToken());
        ((LoginModelRealmProxyInterface) realmObject).realmSet$Stations(((LoginModelRealmProxyInterface) newObject).realmGet$Stations());
        RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListList = ((LoginModelRealmProxyInterface) newObject).realmGet$loginModelRealmList();
        RealmList<com.zebra.rfid.Model.LoginModel> loginModelRealmListRealmList = ((LoginModelRealmProxyInterface) realmObject).realmGet$loginModelRealmList();
        loginModelRealmListRealmList.clear();
        if (loginModelRealmListList != null) {
            for (int i = 0; i < loginModelRealmListList.size(); i++) {
                com.zebra.rfid.Model.LoginModel loginModelRealmListItem = loginModelRealmListList.get(i);
                com.zebra.rfid.Model.LoginModel cacheloginModelRealmList = (com.zebra.rfid.Model.LoginModel) cache.get(loginModelRealmListItem);
                if (cacheloginModelRealmList != null) {
                    loginModelRealmListRealmList.add(cacheloginModelRealmList);
                } else {
                    loginModelRealmListRealmList.add(LoginModelRealmProxy.copyOrUpdate(realm, loginModelRealmListList.get(i), true, cache));
                }
            }
        }
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("LoginModel = proxy[");
        stringBuilder.append("{Password:");
        stringBuilder.append(realmGet$Password() != null ? realmGet$Password() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{AccessToken:");
        stringBuilder.append(realmGet$AccessToken() != null ? realmGet$AccessToken() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{Stations:");
        stringBuilder.append(realmGet$Stations() != null ? realmGet$Stations() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userName:");
        stringBuilder.append(realmGet$userName() != null ? realmGet$userName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{loginModelRealmList:");
        stringBuilder.append("RealmList<LoginModel>[").append(realmGet$loginModelRealmList().size()).append("]");
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
        LoginModelRealmProxy aLoginModel = (LoginModelRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aLoginModel.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLoginModel.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aLoginModel.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
