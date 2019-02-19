package cn.mansys.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * 为Jedis做适配，主要是使用了Jedis的连接池，并做了资源的释放 
 * 
 * @author wuu 2018年12月18日
 */
@Service
public class JedisAdapter implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

	private JedisPool pool;

	@Override
	public void afterPropertiesSet() throws Exception {
		pool = new JedisPool("redis://localhost:6379/8");
	}

	public long sadd(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.sadd(key, value);
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	public long srem(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.srem(key, value);
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	public long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.scard(key);
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	public boolean sismember(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.sismember(key, value);
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	public List<String> brpop(int timeout, String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.brpop(timeout, key);
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public long lpush(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.lpush(key, value);
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}
	
	public Jedis getJedis() {
		return pool.getResource();
	}

	public Transaction multi(Jedis jedis) {
		try {
			return jedis.multi();
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		}
		return null;
	}

	public List<Object> exec(Transaction tr, Jedis jedis) {
		try {
			return tr.exec();
		} catch (Exception e) {
			logger.error("发生异常" + e.getMessage());
		} finally {
			try {
				if (tr != null) {
					tr.close();
				}
			} catch (IOException e) {
				logger.error("发生异常" + e.getMessage());
			}
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	public long zadd(String key,double score,String value) {
		Jedis jedis=null;
		try {
			jedis=pool.getResource();
			return jedis.zadd(key, score, value);
		}catch(Exception e) {
			logger.error("发生异常" + e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		return 0;
	}
	public long zrem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrem(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }
	public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
	/**
	 * 按分值从大到小，取出数据
	 * @param key
	 * @param start	开始值
	 * @param end	结束值
	 * @return
	 */
    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public long zcard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zcard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public Double zscore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zscore(key, member);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public List<String> lrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lrange(key, start, end);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
	
	
	
	
	
}
