package com.dangnian.springboot.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

public class GsonUtils {

	/**
	 * 获得默认代理对象
	 *
	 * @return CollectionUtils
	 */
	public static Gson getProxy(){
		return new Gson();
	}


	/**
	 * 业务对象转化为json字符串
	 *
	 * @param   src 业务对象
	 * @return
	 */
	public static String toJson(Object src){
		Gson gson = new Gson();
		return gson.toJson(src);
	}

	/**
	 * 业务对象转化为json字符串
	 *
	 * @param src     业务对象
	 * @param typeOfT
     * @return
     */
	public static String toJson(Object src, Type typeOfT){
		Gson gson = new Gson();
		return gson.toJson(src,typeOfT);
	}

	/**
	 * 转化为业务对象
	 *
	 * @param json   json字符串
	 * @param clazz  业务类类型
     * @return
     */
	public static <T> T fromJson(String json, Class<T> clazz){
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
	
    /**
     * 转化为业务对象
     * 
     * @param json
     * @param typeOfT Type type = new TypeToken<T>(){}.getType();
     * @return
     */
	public static <T> T fromJson(String json, Type typeOfT){
		Gson gson = new Gson();
		return gson.fromJson(json, typeOfT);
	}

	/**
	 * 转化JsonObject通用类型
	 *
	 * @param   src 业务对象
	 *
	 * @return
     */
	public static JsonObject toJsonObject(Object src){
		if(src != null){
			JsonParser parser = new JsonParser();
			return (JsonObject)parser.parse(GsonUtils.toJson(src));
		}
		return  null;
	}

	/**
	 * 转化字节码
	 *
	 * @param src
	 * @param charsetName
	 * @return
	 */
	public static byte[]  toByte(Object src,String charsetName){
		String dataString = toJson(src);
		if(dataString != null){
			return dataString.getBytes(Charset.forName(charsetName));
		}
		return null;
	}

	/**
	 * 字节转化成对象
	 *
	 * @param dataByte
	 * @param charsetName
	 * @param objClazz
	 * @param <T>
	 * @return
	 */
	public static <T> T toObject(byte[] dataByte,String charsetName,Class<T> objClazz){
		try {
			String dataString = new String(dataByte,charsetName);
			return  fromJson(dataString,objClazz);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字节转化成LIST对象
	 *
	 * @param dataByte
	 * @param charsetName
	 * @param objClazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> toObjectList(byte[] dataByte,String charsetName,Class<T> objClazz) {
		List<T>  objList = null;
		try {
			String dataString = new String(dataByte,charsetName);
			objList = fromJson(dataString,new ParameterizedTypeImpl(objClazz));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return objList;
	}

	public static <T> List<T> toObjectList(String dataStringJson,Class<T> objClazz) {
		return fromJson(dataStringJson,new ParameterizedTypeImpl(objClazz));
	}



	private static class ParameterizedTypeImpl implements ParameterizedType {
		Class clazz;
		public ParameterizedTypeImpl(Class clz) {
			clazz = clz;
		}
		@Override
		public Type[] getActualTypeArguments() {
			return new Type[]{clazz};
		}
		@Override
		public Type getRawType() {
			return List.class;
		}
		@Override
		public Type getOwnerType() {
			return null;
		}
	}

}