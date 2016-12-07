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
 
    private String phone;
    
    private String password;
    
    private String nick;
    
    private Boolean isTemple;
    
    private String portrait;
    
    private String cover;
    
    private String description;
    
    private Byte status;
    
    private Integer level;
    
    private String rankName;

    private Integer score;
    
    private Integer merits;
    
    private Integer prayNum;
    
    private Integer ct;
    
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

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
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

	public Integer getPrayNum() {
		return prayNum;
	}

	public void setPrayNum(Integer prayNum) {
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
    
}
