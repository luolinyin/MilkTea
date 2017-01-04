package com.xiaozhi.frame.local.userdata;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/3.
 */

public class UserData implements Serializable {
    public String shopId;
    public ArrayList<CzyData> czyDatas = new ArrayList<CzyData>();
}
