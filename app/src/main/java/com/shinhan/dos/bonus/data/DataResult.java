package com.shinhan.dos.bonus.data;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Callback;

public interface DataResult {
	/*************************
	 * 카드
	 *************************/
	public void getCardUsage(Callback<Map> callback, Map<String, String> params);
/*
	// 샘플코드 ..
	public void getUserList(Callback<JsonObject> callback);
	public void joinUser(Callback<JsonObject> callback, String name, String uuid, String idNumber, String password);
	public void token(Callback<Map> callback, Map<String, String> params);
	public void inquiryRealName(Callback<Map> callback, String token, Map<String, String> params);
*/

}
