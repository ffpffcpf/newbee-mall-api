package ltd.newbee.mall.api.mapper;

public interface I18nLangMapper<T,V> {
    T mapI18nLangFromEntity(T target, V source, String lang);
}
