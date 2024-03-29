package org.study.springbatch.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Standings extends FootballBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "standings_id")
	private long id;

	@Column(nullable=false)
	private int rank;

	@Column(nullable = false)
	private int points;

	// 골득실차
	@Column(nullable=false)
	private int goalDiff;

	// 최근 경기 전적
	@Size(min=0, max=5)
	@Column(nullable=false, length = 5)
	private String form;

	// 총 경기횟수
	@Column(nullable=false)
	private int played;

	@Column(nullable=false)
	private int win;

	// 무승부
	@Column(nullable=false)
	private int draw;

	@Column(nullable=false)
	private int lose;

	// 득점 수
	@Column(nullable=false)
	private int goalsFor;

	// 실점 수
	@Column(nullable=false)
	private int goalsAgainst;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "standing")
	private SeasonLeagueTeam seasonLeagueTeam;


	@Builder

	public Standings(int rank, int points, int goalDiff, String form, int played, int win, int draw, int lose,
		int goalsFor,
		int goalsAgainst) {
		this.rank = rank;
		this.points = points;
		this.goalDiff = goalDiff;
		this.form = form;
		this.played = played;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
	}
}

