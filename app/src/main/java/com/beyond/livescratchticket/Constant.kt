package com.beyond.livescratchticket

const val TAG = "LiveScratchTicketTAG"

val taskList = arrayListOf(
    FamilyTask(
        name = "洗碗",
        roleImage = RoleImage(
            manImg = R.mipmap.dishwasher_man,
            womanImg = R.mipmap.dishwasher_woman,
        )
    ),
    FamilyTask(
        name = "拖地",
        roleImage = RoleImage(
            manImg = R.mipmap.mop_the_floor_man,
            womanImg = R.mipmap.mop_the_floor_woman,
        )
    ),
    FamilyTask(
        name = "大扫除厕所",
        roleImage = RoleImage(
            manImg = R.mipmap.toilet_man,
            womanImg = R.mipmap.toilet_woman,
        )
    ),
    FamilyTask(
        name = "大扫除厨房",
        roleImage = RoleImage(
            manImg = R.mipmap.clean_the_kitchen_man,
            womanImg = R.mipmap.clean_the_kitchen_woman,
        )
    ),
    FamilyTask(
        name = "擦桌椅柜子",
        roleImage = RoleImage(
            manImg = R.mipmap.clean_a_table_man,
            womanImg = R.mipmap.clean_a_table_woman,
        )
    ),
)