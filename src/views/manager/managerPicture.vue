<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px " placeholder="请输入公告类型" suffix-icon="el-icon-search" v-model="imageText"></el-input>
      <!--      <el-input style="width: 200px " placeholder="请输入" suffix-icon="el-icon-message" v-model="email" class="ml-5"></el-input>-->
      <!--      <el-input style="width: 200px " placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address" class="ml-5"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定删除'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="确定要删除此条数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference" >批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>

      <el-upload action="http://localhost:9090/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>

      <el-button type="primary" @click="exp" class="ml-5">导出 <i class="el-icon-top"></i></el-button>
    </div>
    <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column label="公告图" width="120">
        <template v-slot:default="scope">
          <img :src="scope.row.imageUrl" alt="公告图" style="width: 100%; height: 100%; object-fit: cover">
        </template>
      </el-table-column>
      <el-table-column prop="imageTime" label="公告时间" width="200"></el-table-column>
      <el-table-column prop="imageText" label="公告简述"></el-table-column>
      <el-table-column prop="urlText" label="公告地址"></el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template v-slot="slot">
          <el-button type="success" @click="handleEdit(slot.row)" class="mr-5">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定删除'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定要删除此条数据吗？"
              @confirm="handleDelete(slot.row.id)"
          >
            <el-button type="danger" slot="reference" >删除 <i class="el-icon-delete-solid"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 15]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

    </div>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="40%">
      <el-form label-width="120px">

        <el-form-item label="公告图" >
<!--          <el-input v-model="form.textType" autocomplete="off" style="width: 250px;"></el-input>-->
          <template v-slot="scope">
          <el-upload
              action="http://localhost:9090/manager/pic/upload/pic"

              :on-success="(response, file, fileList) => handleFileUpload(response, file, fileList)"

              list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
          </template>
        </el-form-item>


        <el-form-item label="公告时间" >
          <el-input v-model="form.imageTime" autocomplete="off" style="width: 250px;"></el-input>
        </el-form-item>
        <el-form-item label="公告概述" >
          <el-input v-model="form.imageText" autocomplete="off" style="width: 250px;"></el-input>
        </el-form-item>
        <el-form-item label="公告地址" >
          <el-input v-model="form.urlText" autocomplete="off" style="width: 250px;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="EditCancel">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "managerPicture",
  data(){
    return {
      imageTime: '',
      imageText: '',
      urlText: '',
      image_url: '',
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      uploadUrl: '',
      dataUrl: '',
      imageUrl: '',
      image: '',
    }
  },
  created() {
    this.load()
    console.log(this.form)
  },
  mounted() {
    let image = localStorage.getItem('img_url');
    this.form.image_url = image
  },
  methods:{


    handleFileUpload(response, file, fileList){
        response.imageUrl = file.response.data
        this.$set(response, 'imageUrl', file.response.data)
      localStorage.setItem("image_url", response.data)

      // this.request.put('').then(res => {
      //   this.form.imageUrl = row.imageUrl
      // })


      this.fileList = fileList
    },
    handlePreview(file){
      console.log(file);
    },

    load(){
      //前后端调用方法封装load方法
      this.request.get("/manager/pic/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          image_url: this.image_url,
        }
      })
          .then(res => {
            // console.log(res)

            this.tableData = res.records
            this.total = res.total

          })
    },
    reset(){
      this.imageText = ""
      // this.email = ""
      // this.address = ""
      this.load()
    },
    save(){
      this.form.imageUrl = localStorage.getItem('image_url')
      this.request.post("/manager/pic/",this.form).then(res => {
        if(res){
          this.$message.success("创建成功")
          this.load()
        }else {
          this.$message.error("创建失败")
        }
        this.dialogFormVisible = false
      })
    },
    EditCancel(){
      this.form = {}
      this.dialogFormVisible = false
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row){
      //this.form = Object.assign({},row) // 深拷贝赋值，用于解决点击空白页面也会确认的问题
      this.form = JSON.parse(JSON.stringify(row))   // 另一种写法
      this.dialogFormVisible = true
    },
    handleDelete(id){
      this.request.delete("/manager/pic/" + id).then(res => {
        if(res){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      this.request.delete("/manager/pic/del/batch",{data:ids}).then(res => {
        if(res){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    exp(){
      window.open("http://localhost:9090/user/export")
    },
    handleExcelImportSuccess(){
      this.$message.success("文件导入成功")
      this.load()
    }
  }


}
</script>

<style scoped>

</style>