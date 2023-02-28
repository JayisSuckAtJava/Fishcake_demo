package com.fht.test.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class DetectedOptionPK implements Serializable {

	@Column(name = "o_label")
	private boolean label;
	
	@Column(name = "o_conf")
	private boolean conf;
	
	@Column(name = "o_s_thres")
	private float spectralThreshold;
	
	@Column(name = "o_nms")
	private float nonMaximumSuppression;
	
	@Column(name = "o_v_thres")
	private float visionThreshold;
	
	@Column(name = "o_cuda")
	private boolean cuda;
	
}
