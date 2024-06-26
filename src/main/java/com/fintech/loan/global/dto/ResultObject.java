package com.fintech.loan.global.dto;

import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultObject implements Serializable {

  public String code;

  public String desc;

  public ResultObject(ResultType resultType) {
    this.code = resultType.getCode();
    this.desc = resultType.getDesc();
  }

  public ResultObject(ResultType resultCode, String message) {
    this.code = resultCode.getCode();
    this.desc = message;
  }

  public ResultObject(BaseException e) {
    this.code = e.getCode();
    this.desc = e.getDesc();
  }

  public static ResultObject getSuccess() {
    return new ResultObject(ResultType.SUCCESS);
  }
}
