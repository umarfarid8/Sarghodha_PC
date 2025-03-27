package com.UZdevelopers.sarghodhapc.UI.ModelClass

class Load(
    var id:String?=null,
    var processStatus:String?="1", // 1 Initial Weighment
//2 when quality check is done  3 when unloading is done 4 tear weight is added (cycle completed)
       var materialName:String?="-1",
    var date:String?="-1",
    var vendorName:String?="-1",
    var vendorNumber: String="-1",
    var entryPermit:String?="-1",
    var vechileType:String?="-1",
    var vechileNumber:String?="-1",
    var grossWeight:String?="-1",
    var tareWeight:String?="-1",
    var netWeight:String?="-1",
    var deductionP:String?="-1",
    var payableWeight:String?="-1",
    var stackNumber: String?="-1",
    var mositureP:String?="-1",
    var sandP:String?="-1",
    var mudPieceP:String?="-1",
    var tareWeightTime:String?="-1",
    var burraysP:String?="-1",
    var finesP:String?="-1",
    var colorr:String?="--", // light, dark Brown
    var testedBy:String?="--",
    var status:String?="--",
    var remarks:String?="--",

) {

}