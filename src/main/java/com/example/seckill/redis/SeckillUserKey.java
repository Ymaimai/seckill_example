package com.example.seckill.redis;

public class SeckillUserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;//kookie过期时间，两天

    private SeckillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillUserKey token = new SeckillUserKey(TOKEN_EXPIRE, "tk");
    public static SeckillUserKey getById = new SeckillUserKey(0, "id");
}
