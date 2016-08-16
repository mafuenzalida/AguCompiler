%struct._IO_FILE = type { i32, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, %struct._IO_marker*, %struct._IO_FILE*, i32, i32, i64, i16, i8, [1 x i8], i8*, i64, i8*, i8*, i8*, i8*, i64, i32, [20 x i8] }
%struct._IO_marker = type { %struct._IO_marker*, %struct._IO_FILE*, i32 }

@stdin = external global %struct._IO_FILE*, align 8
@.str = private unnamed_addr constant [4 x i8] c"%ld\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.3 = private unnamed_addr constant [5 x i8] c"true\00", align 1
@.str.4 = private unnamed_addr constant [6 x i8] c"false\00", align 1

; Function Attrs: nounwind uwtable
define i8* @readLine(i64 %x) #0 {
  %1 = tail call noalias i8* @calloc(i64 1024, i64 1) #2
  %2 = load %struct._IO_FILE*, %struct._IO_FILE** @stdin, align 8, !tbaa !1
  %3 = tail call i8* @fgets(i8* %1, i32 1024, %struct._IO_FILE* %2) #2
  ret i8* %1
}

; Function Attrs: nounwind
declare noalias i8* @calloc(i64, i64) #1

; Function Attrs: nounwind
declare i8* @fgets(i8*, i32, %struct._IO_FILE* nocapture) #1

; Function Attrs: nounwind uwtable
define void @printInt(i64 %x) #0 {
  %1 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i64 %x) #2
  ret void
}

; Function Attrs: nounwind
declare i32 @printf(i8* nocapture readonly, ...) #1

; Function Attrs: nounwind uwtable
define void @printChar(i8 signext %x) #0 {
  %1 = sext i8 %x to i32
  %putchar = tail call i32 @putchar(i32 %1) #2
  ret void
}

; Function Attrs: nounwind uwtable
define void @printString(i8* %str) #0 {
  %1 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i8* %str) #2
  ret void
}

; Function Attrs: nounwind uwtable
define void @printBool(i1 %x) #0 {
  br i1 %x, label %1, label %3

; <label>:1                                       ; preds = %0
  %2 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.3, i64 0, i64 0)) #2
  br label %5

; <label>:3                                       ; preds = %0
  %4 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.4, i64 0, i64 0)) #2
  br label %5

; <label>:5                                       ; preds = %1, %3
  ret void
}

; Function Attrs: nounwind uwtable
define void @printNL(i64 %x) #0 {
  %1 = icmp sgt i64 %x, 0
  br i1 %1, label %.lr.ph.preheader, label %._crit_edge

.lr.ph.preheader:                                 ; preds = %0
  br label %.lr.ph

.lr.ph:                                           ; preds = %.lr.ph.preheader, %.lr.ph
  %.01 = phi i64 [ %2, %.lr.ph ], [ %x, %.lr.ph.preheader ]
  %putchar = tail call i32 @putchar(i32 10) #2
  %2 = add nsw i64 %.01, -1
  %3 = icmp sgt i64 %.01, 1
  br i1 %3, label %.lr.ph, label %._crit_edge.loopexit

._crit_edge.loopexit:                             ; preds = %.lr.ph
  br label %._crit_edge

._crit_edge:                                      ; preds = %._crit_edge.loopexit, %0
  ret void
}

; Function Attrs: nounwind
declare i32 @putchar(i32) #2

!1 = !{i64 0}