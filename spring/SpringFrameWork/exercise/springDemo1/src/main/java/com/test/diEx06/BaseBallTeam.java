package com.test.diEx06;

public class BaseBallTeam {
	String manager; //야구감독
	String battingCoach; 
	String pitchingCoach;
	String hitter;
	String pitcher;
	
	public BaseBallTeam() {}
	
	public BaseBallTeam(String manager, String battingCoach, String pitchingCoach) {
		this.manager = manager;
		this.battingCoach = battingCoach;
		this.pitchingCoach = pitchingCoach;
	} //인자 생성자

	// getter, setter
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getBattingCoach() {
		return battingCoach;
	}

	public void setBattingCoach(String battingCoach) {
		this.battingCoach = battingCoach;
	}

	public String getPitchingCoach() {
		return pitchingCoach;
	}

	public void setPitchingCoach(String pitchingCoach) {
		this.pitchingCoach = pitchingCoach;
	}

	public String getHitter() {
		return hitter;
	}

	public void setHitter(String hitter) {
		this.hitter = hitter;
	}

	public String getPitcher() {
		return pitcher;
	}

	public void setPitcher(String pitcher) {
		this.pitcher = pitcher;
	}

}
