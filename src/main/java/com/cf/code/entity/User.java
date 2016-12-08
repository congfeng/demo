/**
 * 
 */
package com.cf.code.entity;


/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public class User {

	private Integer id;
	
	private Integer ct;
	
	private Integer ut;
	
	private Byte status;
 
    private String phone;
    
    private String password;
    
    private Boolean isTemple;
    
    private String nick;
    
    private String portrait;
    
    private String cover;
    
    private String description;
    
    private String levelName;
    
    private Integer level;
    
    private Integer score;
    
    private Integer merits;
    
    private Long prayNum;
    
    private String clientId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Boolean getIsTemple() {
        return isTemple;
    }

    public void setIsTemple(Boolean isTemple) {
        this.isTemple = isTemple;
    }

    public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMerits() {
		return merits;
	}

	public void setMerits(Integer merits) {
		this.merits = merits;
	}

    public Long getPrayNum() {
        return prayNum;
    }

    public void setPrayNum(Long prayNum) {
        this.prayNum = prayNum;
    }

    public Integer getCt() {
        return ct;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getUt() {
        return ut;
    }

    public void setUt(Integer ut) {
        this.ut = ut;
    }
    
}
