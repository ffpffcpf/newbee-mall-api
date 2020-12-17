package ltd.newbee.mall.api.mapper;

public interface I8nLangMapper<T,V> {
    T mapI18nLangFromEntity(T target, V source, String lang);
}
